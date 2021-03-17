module: build.gradle

```json
defaultConfig {
    applicationId "com.cjm.viewmodeldemo"
    minSdkVersion 22
    targetSdkVersion 28
    versionCode 1
    versionName "1.0"
    testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

    dataBinding {
        enabled true
    }
}
```

