package com.tips.zy.tips.Login.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tips.zy.tips.AddPeople.DBHelper.PeopleCharacterHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleHobbyHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleInfoAllHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleInfoHelper;
import com.tips.zy.tips.AddPeople.DBHelper.PeopleWorkHelper;
import com.tips.zy.tips.AddPeople.Entity.PeopleCharacter;
import com.tips.zy.tips.AddPeople.Entity.PeopleHobby;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfo;
import com.tips.zy.tips.AddPeople.Entity.PeopleInfoAll;
import com.tips.zy.tips.AddPeople.Entity.PeopleWork;
import com.tips.zy.tips.Application.MyApplication;
import com.tips.zy.tips.Login.DBHelper.UserHelper;
import com.tips.zy.tips.Login.Enity.User;
import com.tips.zy.tips.Main.Activity.MainActivity;
import com.tips.zy.tips.Main.Entity.Group;
import com.tips.zy.tips.Main.Entity.People;
import com.tips.zy.tips.Main.Entity.PeopleAll;
import com.tips.zy.tips.Main.Entity.PeopleGroupAll;
import com.tips.zy.tips.R;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.util.Log;

import static android.Manifest.permission.READ_CONTACTS;
import static com.tips.zy.tips.R.layout.group;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoaderCallbacks<Cursor> ,OnClickListener{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private TextView regist;
    private TextView foundPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
        initData();
        initEvent();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } /*else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }*/


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    public static Intent CreateIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        regist= (TextView) findViewById(R.id.regist);
        foundPass= (TextView) findViewById(R.id.foundPass);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        regist.setOnClickListener(this);
        foundPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id){
            case R.id.regist:{
                startActivity(RegistActivity.CreateIntent(context));
                break;
            }
            case R.id.foundPass:{
                startActivity(FoundPassActivity.CreateIntent(context));
                break;
            }
        }
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, User> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected User doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
                UserHelper userHelper=new UserHelper(context);
                User user=userHelper.query(mEmail,mPassword);
                Log.d("user",user.toString());
                if(user.getU_name()!=null){
                    queryAll();
                    return user;
                }
                return null;

            } catch (InterruptedException e) {
                return null;
            }

            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/

           /* // TODO: register the new account here.
            return null;*/
        }

        @Override
        protected void onPostExecute(final User user) {
            mAuthTask = null;
            showProgress(false);

            if (user!=null) {
                //将User 放进全局变量
                getMyApplication().setUser(user);
                startActivity(new Intent(context,MainActivity.class));
                finish();

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
    public MyApplication getMyApplication(){
        return (MyApplication) getApplicationContext();
    }
    public void queryAll(){
        int icons[]=new int[]{R.mipmap.icon1,R.mipmap.icon2,
                R.mipmap.icon3,R.mipmap.icon4,
                R.mipmap.icon5,R.mipmap.icon6,
                R.mipmap.icon7,R.mipmap.icon8,R.mipmap.icon9};
        PeopleInfoAllHelper peopleInfoAllHelper=new PeopleInfoAllHelper(context);
        List<String>G_Names=peopleInfoAllHelper.queryG_Name();
        Log.d("G_Names",G_Names.toString());
        List<PeopleGroupAll> peopleGroupAlls=new ArrayList<>();

        //生成Groups
        List<Group>groups=new ArrayList<>();

        for(int j=0;j<G_Names.size();j++){
            PeopleGroupAll peopleGroupAll=new PeopleGroupAll();
            peopleGroupAll.setGroup(G_Names.get(j));
            List<PeopleAll>peopleAlls=new ArrayList<>();
            List<PeopleInfoAll>peopleInfoAlls =peopleInfoAllHelper.queryByG_Name(G_Names.get(j));
            //Group
            Group group=new Group();
            group.setGroupName(G_Names.get(j));
            List<People> peoples=new ArrayList<>();
            for (int i= 0; i  < peopleInfoAlls.size(); i++) {

                    PeopleAll peopleAll = new PeopleAll();
                    People people=new People();
                    //people.setIcon(icons[new Random().nextInt(1)]);
                    Log.d("查询peopleInfoAll", peopleInfoAlls.get(i).toString());
                    //查询peopleInfo
                    int P_Id = peopleInfoAlls.get(i).getP_Id();
                    PeopleInfoHelper peopleInfoHelper = new PeopleInfoHelper(context);
                    PeopleInfo peopleInfo = peopleInfoHelper.queryById(P_Id);
                    Log.d("查询查询peopleInfo", peopleInfo.toString());
                    peopleAll.setPeopleInfo(peopleInfo);
                    people.setP_Id(peopleInfo.getP_Id());
                    people.setP_Name(peopleInfo.getP_Name());
                    people.setIcon(peopleInfo.getP_Icon());
                    //查询peopleWork
                    int W_Id = peopleInfoAlls.get(i).getW_Id();
                    PeopleWorkHelper peopleWorkHelper = new PeopleWorkHelper(context);
                    PeopleWork peopleWork = peopleWorkHelper.queryById(W_Id);
                    Log.d("查询peopleWork", peopleWork.toString());
                    peopleAll.setPeopleWork(peopleWork);
                    //查询PeopleHobby
                    int H_Id = peopleInfoAlls.get(i).getH_Id();
                    PeopleHobbyHelper peopleHobbyHelper = new PeopleHobbyHelper(context);
                    PeopleHobby peopleHobby = peopleHobbyHelper.queryById(H_Id);
                    Log.d("查询PeopleHobby", peopleHobby.toString());
                    peopleAll.setPeopleHobby(peopleHobby);
                    people.setP_Hobby(peopleHobby.getH_field()+peopleHobby.getH_Sport());
                    //查询PeopleCharactor
                    int C_Id = peopleInfoAlls.get(i).getC_Id();
                    PeopleCharacterHelper peopleCharacterHelper = new PeopleCharacterHelper(context);
                    PeopleCharacter peopleCharacter = peopleCharacterHelper.queryById(C_Id);
                    Log.d("查询peopleCharacters", peopleCharacter.toString());
                    peopleAll.setPeopleCharacter(peopleCharacter);

                    peopleAlls.add(peopleAll);
                    peoples.add(people);
            }
            peopleGroupAll.setPeopleAlls(peopleAlls);
            peopleGroupAlls.add(peopleGroupAll);
            Log.d("peopleGroupAlls",peopleGroupAlls.toString());
            getMyApplication().setPeopleGroupAlls(peopleGroupAlls);
            //添加Group
            group.setPeoples(peoples);
            groups.add(group);
            getMyApplication().setGroups(groups);

        }
    }
    /*public void addPeopleAll(){
        List<PeopleGroupAll> peopleGroupAlls=getMyApplication().getPeopleGroupAlls();

        //查询peopleInfoAll
        PeopleInfoAllHelper peopleInfoAllHelper=new PeopleInfoAllHelper(context);
        List<PeopleInfoAll> peopleInfoAlls=peopleInfoAllHelper.query();
        for(int j=0;j<peopleInfoAlls.size();j++){
            PeopleGroupAll peopleGroupAll = new PeopleGroupAll();
            peopleGroupAll.setGroup(peopleInfoAlls.get(j).getG_Name());
            List<PeopleAll> peopleAlls=new ArrayList<>();

            for (int i = 0; i < peopleInfoAlls.size(); i++) {
                if (peopleGroupAll.getGroup().equals(peopleInfoAlls.get(i).getG_Name())) {
                    PeopleAll peopleAll = new PeopleAll();
                    Log.d("查询peopleInfoAll", peopleInfoAlls.get(i).toString());
                    //查询peopleInfo
                    int P_Id = peopleInfoAlls.get(i).getP_Id();
                    PeopleInfoHelper peopleInfoHelper = new PeopleInfoHelper(context);
                    PeopleInfo peopleInfo = peopleInfoHelper.queryById(P_Id);
                    Log.d("查询查询peopleInfo", peopleInfo.toString());
                    peopleAll.setPeopleInfo(peopleInfo);
                    //查询peopleWork
                    int W_Id = peopleInfoAlls.get(i).getW_Id();
                    PeopleWorkHelper peopleWorkHelper = new PeopleWorkHelper(context);
                    PeopleWork peopleWork = peopleWorkHelper.queryById(W_Id);
                    Log.d("查询peopleWork", peopleWork.toString());
                    peopleAll.setPeopleWork(peopleWork);
                    //查询PeopleHobby
                    int H_Id = peopleInfoAlls.get(i).getH_Id();
                    PeopleHobbyHelper peopleHobbyHelper = new PeopleHobbyHelper(context);
                    PeopleHobby peopleHobby = peopleHobbyHelper.queryById(H_Id);
                    Log.d("查询PeopleHobby", peopleHobby.toString());
                    peopleAll.setPeopleHobby(peopleHobby);
                    //查询PeopleCharactor
                    int C_Id = peopleInfoAlls.get(i).getC_Id();
                    PeopleCharacterHelper peopleCharacterHelper = new PeopleCharacterHelper(context);
                    PeopleCharacter peopleCharacter = peopleCharacterHelper.queryById(C_Id);
                    Log.d("查询peopleCharacters", peopleCharacter.toString());
                    peopleAll.setPeopleCharacter(peopleCharacter);
                    peopleAlls.add(peopleAll);
                    peopleGroupAll.setPeopleAlls(peopleAlls);
                }else{
                    j=i;
                    peopleGroupAlls.add(peopleGroupAll);
                    break;
                }

            }
        }
    }*/
}

