package daniel.chatmodel.features.chat.chatList

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.base.Failure
import daniel.chatmodel.base.State
import daniel.chatmodel.base.Success
import daniel.chatmodel.base.firestore.CHATS
import daniel.chatmodel.base.firestore.MESSAGES
import daniel.chatmodel.base.firestore.SENT
import daniel.chatmodel.features.chat.MessageModel
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.Exception

private const val TAG = "MessageRepository"

class MessageRepository {
    private val database = Firebase.firestore
    private val listenerMap: MutableMap<String, ListenerRegistration> = mutableMapOf()

    fun listenLastMessage(chatId: String): Flow<State<MessageModel>> {
        return callbackFlow {
            val query = lastMessageQuery(chatId)
            val listener = query.addSnapshotListener { snap, err ->
                handleMessageUpdates(snap, err)
            }
            listenerMap[chatId] = listener

            awaitClose {
                stopListenLastMessage(chatId)
            }
        }
    }

    private fun stopListenLastMessage(chatId: String){
        listenerMap[chatId]?.remove()
        listenerMap.remove(chatId)
    }

    private fun lastMessageQuery(chatId: String): Query {
        return database
            .collection(CHATS)
            .document(chatId)
            .collection(MESSAGES)
            .orderBy(SENT, Query.Direction.DESCENDING)
            .limit(1)
    }

    private fun ProducerScope<State<MessageModel>>.handleMessageUpdates(
        snap: QuerySnapshot?,
        err: FirebaseFirestoreException?
    ) {
        if (err != null) trySend(Failure(err))
        val noMessagesSent = snap == null
        if (noMessagesSent) {
            trySend(Success(MessageModel()))
            return
        }
        val messages = snap!!.toObjects(MessageModel::class.java)
        val lastMessage = messages.getOrNull(0)
        if (lastMessage == null) {
            trySend(Success(MessageModel()))
            return
        }

        trySend(Success(lastMessage))
    }

}