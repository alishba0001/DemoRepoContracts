package com.example.contracts;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//The contract defines the type of input and output used to start the activity and retrieve its result.
public class SecondActivityContract extends ActivityResultContract<SecondActivityParameters, String> {

    public Intent createIntent(@NonNull Context context, SecondActivityParameters secondActivityParameter) {

        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(SecondActivityParameters.EXTRA_FIRST_NAME,secondActivityParameter.getFirstName());
        intent.putExtra(SecondActivityParameters.EXTRA_SECOND_NAME, secondActivityParameter.getSecondName());

        return intent;
    }

    @Override
    public String parseResult(int resultCode, @Nullable Intent intent) {
        return intent.getData().toString();
    }
}



class SecondActivityParameters{
    private  String firstName;
    private  String secondName;

    public static final String EXTRA_FIRST_NAME = "First Name";
    public static final String EXTRA_SECOND_NAME = "Second Name";

    public SecondActivityParameters() {
    }

    public SecondActivityParameters(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


}