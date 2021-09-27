package daniel.chatmodel.livedata

import androidx.lifecycle.MutableLiveData

class UserCredentials {

    var email: MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")
}