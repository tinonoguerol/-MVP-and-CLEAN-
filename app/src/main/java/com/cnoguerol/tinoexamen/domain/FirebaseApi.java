package com.cnoguerol.tinoexamen.domain;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.cnoguerol.tinoexamen.entities.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by cnoguerol.
 */
public class FirebaseApi {

    private DatabaseReference dataReference;
    private ChildEventListener photosEventListener;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private Uri downloadUrl;
    private FirebaseStorage mStorage;
    private StorageReference mStorageReference;

    private String stringUri;
    private UploadTask uploadTask;

    public FirebaseApi(DatabaseReference dataReference) {
        this.dataReference = FirebaseDatabase.getInstance().getReference();
        this.mAuth = mAuth.getInstance();
        this.mStorage = FirebaseStorage.getInstance();
        this.mStorageReference = mStorage.getReferenceFromUrl("gs://myexamen-a9c7a.appspot.com");
    }

    public String getCurrentUserId() {
        mUser = mAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            return mUser.getUid();
        }
        return null;
    }

    public void checkForData(final DatabaseActionListenerCallback listener) {
        dataReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    listener.onSuccess();
                } else {
                   /* sino no tiene datos por eso enviamos Null ya
                   que  no es error.
                    */
                    listener.onError(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onError(databaseError);
            }
        });


    }


    public void subscribe(final FirebaseEventListenerCallback listener) {
        if (photosEventListener == null) {
            photosEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listener.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listener.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onCancelled(databaseError);
                }
            };
            dataReference.addChildEventListener(photosEventListener);
        }
    }


    public void unsubscribe() {
        if (photosEventListener != null) {
            dataReference.removeEventListener(photosEventListener);
        }
    }


    public String create() {
        // obtenemos el key para cloudinary
        return dataReference.push().getKey();
    }


    public void update(Photo photo) {
        dataReference.child(photo.getId()).setValue(photo);

    }


    public void remove(Photo photo, DatabaseActionListenerCallback listenerCallback) {
        dataReference.child(photo.getId()).removeValue();
        listenerCallback.onSuccess();
    }


    /**
     * Login
     */

    public void signUp(final String email, final String password, final FirebaseActionListenerCallback listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    listener.onAuthSuccess();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    listener.onAuthFailed(e.getMessage());
                }
            });
    }


    public void signIn(String email, String password, final FirebaseActionListenerCallback listener) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    listener.onAuthSuccess();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    listener.onAuthFailed(e.getMessage());
                }
            });
    }

    public String getAuthEmail() {
        String email = null;
        mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            email = mUser.getEmail().toString();
        }
        return email;
    }

    public void checkForSession(final FirebaseActionListenerCallback listener) {
        if (mAuth.getCurrentUser() != null) {
            listener.onAuthSuccess();
        } else {
            listener.onAuthFailed(null);
        }

    }

    public void logout() {
        mAuth.getInstance().signOut();
    }


    public void subirFoto(String url, final double lat, final double log, final StorageActionListenerCallback listener) {

        final String newPhotoId = create();
        final Photo photo = new Photo();
        Uri file = Uri.fromFile(new File(url));

        StorageReference riversRef = mStorageReference.child("images_examen/" + file.getLastPathSegment());

        uploadTask = riversRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onLoadFailed(e.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                listener.onLoadSuccess();
                downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();
                stringUri = downloadUrl.toString();
                photo.setId(newPhotoId);
                photo.setEmail(getAuthEmail());
                photo.setLatitutde(lat);
                photo.setLongitude(log);
                photo.setUrl(stringUri);
                update(photo);

            }
        });

    }
}
