package dhbw.verwaltung.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dhbw.verwaltung.MainActivity;
import dhbw.verwaltung.R;
import dhbw.verwaltung.util.OnScreenNotificationHandler;

/**
 * A {@link Fragment} to display and handle user-profile information.
 */
public class ProfileFragment extends Fragment {

    private boolean allowEdits = true;

    private String userName, userMatNr, userMail, userClass, userMajor, userDeepening;
    private String identName, identMatNr, identMail, identClass, identMajor, identDeepening;

    private SharedPreferences sharedPref;

    private TextView greetingsView, nameView, matNrView, mailView, classView, majorView, deepeningView;
    private EditText nameEdit, matNrEdit, mailEdit, classEdit, majorEdit, deepeningEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        userName = sharedPref.getString(getString(R.string.key_name), "");
        userMatNr = String.valueOf(sharedPref.getInt(getString(R.string.key_matNr), 0));
        userMail = sharedPref.getString(getString(R.string.key_mail), "");
        userClass = sharedPref.getString(getString(R.string.key_class), "");
        userMajor = sharedPref.getString(getString(R.string.key_major), "");
        userDeepening = sharedPref.getString(getString(R.string.key_deepening), "");

        identName = getString(R.string.profile_name);
        identMatNr = getString(R.string.profile_matNr);
        identMail = getString(R.string.profile_mail);
        identClass = getString(R.string.profile_class);
        identMajor = getString(R.string.profile_major);
        identDeepening = getString(R.string.profile_deepening);


        greetingsView = getView().findViewById(R.id.profile_greetings);

        nameView = getView().findViewById(R.id.profile_nameView);
        nameEdit = getView().findViewById(R.id.profile_editName);

        matNrView = getView().findViewById(R.id.profile_matNrView);
        matNrEdit = getView().findViewById(R.id.profile_editMatNr);

        mailView = getView().findViewById(R.id.profile_mailView);
        mailEdit = getView().findViewById(R.id.profile_editMail);

        classView = getView().findViewById(R.id.profile_classView);
        classEdit = getView().findViewById(R.id.profile_editClass);

        majorView = getView().findViewById(R.id.profile_majorView);
        majorEdit = getView().findViewById(R.id.profile_editMajor);

        deepeningView = getView().findViewById(R.id.profile_deepeningView);
        deepeningEdit = getView().findViewById(R.id.profile_editDeepening);


        init(userName, identName, nameView, nameEdit);
        init(userMatNr, identMatNr, matNrView, matNrEdit);
        init(userMail, identMail, mailView, mailEdit);
        init(userClass, identClass, classView, classEdit);
        init(userMajor, identMajor, majorView, majorEdit);
        init(userDeepening, identMajor, deepeningView, deepeningEdit);

        refreshViews();

        final Button saveChanges = getView().findViewById(R.id.profile_saveChanges);

        if (allowEdits) {
            saveChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveChanges(true);
                }
            });

            saveChanges.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b)
                        saveChanges(true);
                }
            });
        } else
            saveChanges.setVisibility(View.GONE);
    }

    private void init(String value, String desc, final TextView textView, final EditText editText) {
        editText.setHint(getString(R.string.profile_hint, desc));
        editText.setText(value);

        if (allowEdits) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.GONE);
                    editText.setVisibility(View.VISIBLE);
                    editText.requestFocus();
                }
            });

            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        view.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);

                        saveChanges(false);
                    }
                }
            });
        }
    }

    private void saveChanges(boolean saveToConfig) {
        String name = ((EditText) getView().findViewById(R.id.profile_editName)).getText().toString();
        String matNr = ((EditText) getView().findViewById(R.id.profile_editMatNr)).getText().toString();
        String mail = ((EditText) getView().findViewById(R.id.profile_editMail)).getText().toString();
        String classS = ((EditText) getView().findViewById(R.id.profile_editClass)).getText().toString();
        String major = ((EditText) getView().findViewById(R.id.profile_editMajor)).getText().toString();
        String deepening = ((EditText) getView().findViewById(R.id.profile_editDeepening)).getText().toString();

        setUserName(name);
        setUserMatNr(matNr);
        setUserMail(mail);
        setUserClass(classS);
        setUserMajor(major);
        setUserDeepening(deepening);

        refreshViews();

        if (saveToConfig) {
            SharedPreferences.Editor sharedPrefEdit = sharedPref.edit();

            sharedPrefEdit.putString(getString(R.string.key_name), name);
            sharedPrefEdit.putInt(getString(R.string.key_matNr), Integer.parseInt(matNr));
            sharedPrefEdit.putString(getString(R.string.key_mail), mail);
            sharedPrefEdit.putString(getString(R.string.key_class), classS);
            sharedPrefEdit.putString(getString(R.string.key_major), major);
            sharedPrefEdit.putString(getString(R.string.key_deepening), deepening);

            if (sharedPrefEdit.commit())
                OnScreenNotificationHandler.displayToast(getContext(), getString(R.string.messages_changesSaved), Toast.LENGTH_SHORT);
            else
                OnScreenNotificationHandler.displayToast(getContext(), getString(R.string.messages_changesNotSaved), Toast.LENGTH_SHORT);

            ((MainActivity) getActivity()).refreshNavHeader();
        }
    }

    private void refreshViews() {
        greetingsView.setText(getString(R.string.profile_greetings, userName));

        refreshView(nameView, nameEdit, identName, userName);
        refreshView(matNrView, matNrEdit, identMatNr, userMatNr);
        refreshView(mailView, mailEdit, identMail, userMail);
        refreshView(classView, classEdit, identClass, userClass);
        refreshView(majorView, majorEdit, identMajor, userMajor);
        refreshView(deepeningView, deepeningEdit, identDeepening, userDeepening);

    }

    private void refreshView(TextView textView, EditText editText, String identifier, String text) {
        textView.setText(getString(R.string.profile_viewContent, identifier, text));
        editText.setText(text);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMatNr() {
        return userMatNr;
    }

    public void setUserMatNr(String userMatNr) {
        this.userMatNr = userMatNr;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public String getUserDeepening() {
        return userDeepening;
    }

    public void setUserDeepening(String userDeepening) {
        this.userDeepening = userDeepening;
    }

    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(TextView nameView) {
        this.nameView = nameView;
    }

    public TextView getMatNrView() {
        return matNrView;
    }

    public void setMatNrView(TextView matNrView) {
        this.matNrView = matNrView;
    }

    public TextView getMailView() {
        return mailView;
    }

    public void setMailView(TextView mailView) {
        this.mailView = mailView;
    }

    public TextView getClassView() {
        return classView;
    }

    public void setClassView(TextView classView) {
        this.classView = classView;
    }

    public TextView getMajorView() {
        return majorView;
    }

    public void setMajorView(TextView majorView) {
        this.majorView = majorView;
    }

    public EditText getNameEdit() {
        return nameEdit;
    }

    public void setNameEdit(EditText nameEdit) {
        this.nameEdit = nameEdit;
    }

    public EditText getMatNrEdit() {
        return matNrEdit;
    }

    public void setMatNrEdit(EditText matNrEdit) {
        this.matNrEdit = matNrEdit;
    }

    public EditText getMailEdit() {
        return mailEdit;
    }

    public void setMailEdit(EditText mailEdit) {
        this.mailEdit = mailEdit;
    }

    public EditText getClassEdit() {
        return classEdit;
    }

    public void setClassEdit(EditText classEdit) {
        this.classEdit = classEdit;
    }

    public EditText getMajorEdit() {
        return majorEdit;
    }

    public void setMajorEdit(EditText majorEdit) {
        this.majorEdit = majorEdit;
    }
}
