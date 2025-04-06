# Run app:
1. Create file api.properties in root project and paste the following:
```
flickrApi = // Your api key
```
or run following command:
```bash
echo "flickrApi = Your api key" > api.properties
```

2. [Generate keystore](https://developer.android.com/studio/publish/app-signing#generate-key) and [create file](https://developer.android.com/studio/publish/app-signing#secure-shared-keystore) `keystore.properties` in root project with fields: `storePassword`, `keyPassword`, `keyAlias`, `storeFile`
3. Run app
