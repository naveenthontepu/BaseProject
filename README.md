# Base Project
Presently this project is just the initial project that android gives when New project is clicked.

Will Update the project with all kinds of libs and base code that every normal Android application would require.
# Usage
### 1. Changing Package Name
* Go to Manifest.xml
* Right click -> Refactor -> Rename / Shift+F6(mac)  on every individual folder name(a package name is made near of 2 or more folders)

### 2. Crashlytics
* Add Crashlytics key in manifest file and your good to go.

### 3. Application Class
* **BaseProject.class** is the Application class refactor it if you need.
* Application class initializes **Shared Preferences class** in onCreate.

### 4. Shared Preferences class
* Live template is given copy it and save for saving time.

### 5. Logs
* Use `Utilities.printLog("onCreate");` or `Utilities.printLog("TAG","onCreate");`
* This will prevent logs to be printed in release version.

