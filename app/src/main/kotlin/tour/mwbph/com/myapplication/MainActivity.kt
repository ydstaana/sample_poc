package tour.mwbph.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.location.Location;

import android.location.LocationManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.FusedLocationProviderApi
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult
import android.widget.Toast
import android.widget.TextView



class MainActivity : AppCompatActivity(),  ConnectionCallbacks,  OnConnectionFailedListener{
    //private lateinit var fusedLocationProviderApi : FusedLocationProviderApi
    private lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mLocationRequest : LocationRequest

    override protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build()

        mGoogleApiClient.connect()

        val mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


    }

    override fun onConnected(bundle: Bundle?) {
        val location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        if (location == null) {
            println("NULL")
        }
        else {
            var latitude : TextView  = findViewById(R.id.latitude) as TextView
            var longitude : TextView = findViewById(R.id.longitude) as TextView

            latitude.setText(location.getLatitude().toString())
            longitude.setText(location.getLongitude().toString())

            println(location.getLatitude())
            println(location.getLongitude())
        }
        println("NOTIF : Connected")
        //Toast.makeText(this, "Connected to Google Play Services", Toast.LENGTH_SHORT).show()
    }

    override fun onConnectionSuspended(cause : Int) {
        println("NOTIF : Suspended")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        println("NOTIF : Failed")
        // code to handle failed connection
        // this code can be found here — http://developer.android.com/training/location/retrieve-current.html
    }




}
