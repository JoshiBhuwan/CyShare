package com.example.cyshare_frontend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cyshare_frontend.model.backend_io;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class AndroidTests {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.CyShare_Frontend", appContext.getPackageName());
    }

    @Test
    public void testLoginSpeed() {
        long startTime = System.currentTimeMillis();
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RequestQueue volleyQueue = Volley.newRequestQueue(context);
        HashMap<String, ArrayList<String>> realData = new HashMap<>();
        String url = "http://coms-309-031.cs.iastate.edu:8080/users/all";
        backend_io.serverGetUserTables(volleyQueue, url, realData);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        assertTrue(elapsed < 5000L);
    }

    /*
    @Test
    public void testLogin1() {
        //region $state
        login_page mockPage = mock(login_page.class);
        mockPage.usersInfo = new HashMap<>();
        doNothing().when(mockPage).to_registration();
        doNothing().when(mockPage).to_driver_splash();
        doNothing().when(mockPage).to_passenger_splash();
        doCallRealMethod().when(mockPage).validate(anyString(), anyString());
        ArrayList<String> tempUser1 = new ArrayList<>();
        tempUser1.add("1");
        tempUser1.add("password");
        tempUser1.add("PASSENGER");
        ArrayList<String> tempUser2 = new ArrayList<>();
        tempUser2.add("2");
        tempUser2.add("password");
        tempUser2.add("DRIVER");
        mockPage.usersInfo.put("josh", tempUser1);
        mockPage.usersInfo.put("jim", tempUser2);
        //endregion

        //region $tests
        mockPage.validate("notauser", "password");
        verify(mockPage, times(0)).to_passenger_splash();
        verify(mockPage, times(0)).to_driver_splash();
        verify(mockPage, times(0)).to_registration();
        //endregion
    }

    @Test
    public void testLogin2() {
        //region $state
        login_page mockPage = mock(login_page.class);
        mockPage.usersInfo = new HashMap<>();
        doNothing().when(mockPage).to_registration();
        doNothing().when(mockPage).to_driver_splash();
        doNothing().when(mockPage).to_passenger_splash();
        doCallRealMethod().when(mockPage).validate(anyString(), anyString());
        ArrayList<String> tempUser1 = new ArrayList<>();
        tempUser1.add("1");
        tempUser1.add("password");
        tempUser1.add("PASSENGER");
        ArrayList<String> tempUser2 = new ArrayList<>();
        tempUser2.add("2");
        tempUser2.add("password");
        tempUser2.add("DRIVER");
        mockPage.usersInfo.put("josh", tempUser1);
        mockPage.usersInfo.put("jim", tempUser2);
        //endregion

        //region $tests
        mockPage.validate("josh", "wrongpass");
        verify(mockPage, times(0)).to_passenger_splash();
        verify(mockPage, times(0)).to_driver_splash();
        verify(mockPage, times(0)).to_registration();
        //endregion
    }
    */

    @Test
    public void testLogin3() {
        //region $state
        login_page mockPage = mock(login_page.class);
        mockPage.usersInfo = new HashMap<>();
        doNothing().when(mockPage).to_registration();
        doNothing().when(mockPage).to_driver_splash();
        doNothing().when(mockPage).to_passenger_splash();
        doCallRealMethod().when(mockPage).validate(anyString(), anyString());
        ArrayList<String> tempUser1 = new ArrayList<>();
        tempUser1.add("1");
        tempUser1.add("password");
        tempUser1.add("PASSENGER");
        ArrayList<String> tempUser2 = new ArrayList<>();
        tempUser2.add("2");
        tempUser2.add("password");
        tempUser2.add("DRIVER");
        mockPage.usersInfo.put("josh", tempUser1);
        mockPage.usersInfo.put("jim", tempUser2);
        //endregion

        //region $tests
        mockPage.validate("josh", "password");
        verify(mockPage, atLeast(1)).to_passenger_splash();
        verify(mockPage, times(0)).to_driver_splash();
        verify(mockPage, times(0)).to_registration();
        //endregion
    }

    @Test
    public void testLogin4() {
        //region $state
        login_page mockPage = mock(login_page.class);
        mockPage.usersInfo = new HashMap<>();
        doNothing().when(mockPage).to_registration();
        doNothing().when(mockPage).to_driver_splash();
        doNothing().when(mockPage).to_passenger_splash();
        doCallRealMethod().when(mockPage).validate(anyString(), anyString());
        ArrayList<String> tempUser1 = new ArrayList<>();
        tempUser1.add("1");
        tempUser1.add("password");
        tempUser1.add("PASSENGER");
        ArrayList<String> tempUser2 = new ArrayList<>();
        tempUser2.add("2");
        tempUser2.add("password");
        tempUser2.add("DRIVER");
        mockPage.usersInfo.put("josh", tempUser1);
        mockPage.usersInfo.put("jim", tempUser2);
        //endregion

        //region $tests
        mockPage.validate("jim", "password");
        verify(mockPage, times(0)).to_passenger_splash();
        verify(mockPage, atLeast(1)).to_driver_splash();
        verify(mockPage, times(0)).to_registration();
        //endregion
    }

}