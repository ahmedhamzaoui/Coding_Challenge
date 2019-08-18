package ahmed.dev.com.coding_challenge;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mUserId;
    private String key;

    public Upload() {
    }

    public Upload(String name, String imageUrl,String userId) {
        if(name.trim().equals("")){
            name = "No Name";
        }

        this.mName = name;
        this.mImageUrl = imageUrl;
        this.mUserId = userId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "mName='" + mName + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                ", mUserId='" + mUserId + '\'' +
                '}';
    }
}
