package com.drunkornot.lingudetect.lingu;

public interface IUserDataChangeListener {
    public void onUserDataReceivedFromDatabase(UserData userData);

    public void onUserDataReceivedFailure();

}
