package com.example.wazitoecommerce.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.wazitoecommerce.models.Property
import com.example.wazitoecommerce.navigation.LOGIN_URL
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class PropertyViewModel(var navController:NavHostController, var context: Context) {
    var authViewModel:AuthViewModel
    var progress:ProgressDialog
    init {
        authViewModel = AuthViewModel(navController, context)
        if (!authViewModel.isLoggedIn()){
            navController.navigate(LOGIN_URL)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun uploadProperty(
        propertyType: String,
        price: String,
        buyORrent: String,
        propertyLocation: String,
        phoneNumber: String,
        id: String,
        filePath: Uri){
        val PropertyId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("Properties/$PropertyId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUri = it.toString()
                    var property = Property(
                        propertyType,
                        price,
                        buyORrent,
                        propertyLocation,
                        phoneNumber,
                        imageUri,
                        id
                    )
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("Properties/$PropertyId")
                    databaseRef.setValue(property).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun allProperties(
        property:MutableState<Property>,
        properties:SnapshotStateList<Property>):SnapshotStateList<Property>{
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Properties")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                properties.clear()
                for (snap in snapshot.children){
                    var retrievedProperty = snap.getValue(Property::class.java)
                    property.value = retrievedProperty!!
                    properties.add(retrievedProperty)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return properties
    }

    fun deleteProperty(propertyId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Products/$propertyId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}