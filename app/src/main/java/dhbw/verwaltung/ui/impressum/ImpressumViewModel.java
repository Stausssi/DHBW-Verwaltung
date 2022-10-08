package dhbw.verwaltung.ui.impressum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImpressumViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ImpressumViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}