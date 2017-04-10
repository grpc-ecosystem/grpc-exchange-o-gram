Adding MediaService
===================

The `./proto/exchange_o_gram.proto` contains all the definitions for `WallService` from the wall service demo, but adds definitions of `MediaService` that allows
adding photos to wall posts.

```
service MediaService {
    rpc UploadImage (UploadImageRequest) returns (UploadImageResponse) {}
    rpc DownloadImage(DownloadImageRequest) returns (DownloadImageResponse) {}
}
```

The `media_id` field was added to `WallPost` message with a previously unused tag, so backwards compatibility is ensured.
```
message WallPost {
    WallPostId id = 1;
    string username = 2;
    string caption = 3;
    int64 timestamp_created = 4;
    MediaId media_id = 5;  // this field added in a backwards compatible fashion! 
}

Both server-side & client-side logic has been added to the Java and C# code.

Building and running the application works the same way as in `1-wallservice` step.
