package dhbw.verwaltung.ui.secretariat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SecretariatViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SecretariatViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}