This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

[To run the desktop app](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application-on-desktop)
Or just click "Play" btn in the file 'main.kt' in desktopMain module
- ERROR 'No MainKt'
  - handling:
    - run in AS terminal - $ ./gradlew run 

[To run the web application](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-web-application) 
Or try running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

To run iosApp:
- install 'XCode' (Apple developer tool) in 'App Store'
- install 'Android studio' (AS)
- install 'Kotlin Multiplatform' plugin in AS
- In the list of run configurations, click Edit Configurations.
- Click the '+' button
- select iOS Application.
- Name your configuration e.g.: 'iosApp'.
- Select the Xcode project file. To do so, navigate to your project 'japa', open the iosApp folder, and then select the .xcodeproj file.
- In the Execution target list, select a simulated device and click OK.
- Click Run to run your application on the new simulated device.

ERRORS handling:
- ERROR: 'line 7: ./gradlew: Permission denied Command PhaseScriptExecution failed with a nonzero exit code'
  - handling: 'chmod +x gradlew' - run it in the terminal in project folder 
- ERROR: 'The operation couldn’t be completed. Unable to locate a Java Runtime.'
  - handling: 
    - [download java](https://www.java.com/en/download/) v22.
    - run downloaded .dmg file
    - install jdk (check 'java -version' in terminal)
    - in case You need [to uninstall Java](https://www.java.com/en/download/help/mac_uninstall_java.html): 
      - In the Terminal window Copy and Paste the commands below:
        sudo rm -fr /Library/Internet\ Plug-Ins/JavaAppletPlugin.plugin
        sudo rm -fr /Library/PreferencePanes/JavaControlPanel.prefPane
        sudo rm -fr ~/Library/Application\ Support/Oracle/Java
        Do not attempt to uninstall Java by removing the Java tools from /usr/bin. This directory is part of the system software and any changes will be reset by Apple the next time you perform an update of the OS.
- ERROR: 'The following build commands failed: PhaseScriptExecution Compile\ Kotlin\ Framework ...'
  - handling:
    - uninstall java jre with instructions above
    - [download](https://www.oracle.com/pe/java/technologies/downloads/#jdk22-mac) and install jdk last version (v22).
- ERROR: 'Execution failed for task ':kotlinStoreYarnLock'.'
  - handling:
    run in AS terminal - $ ./gradlew kotlinUpgradeYarnLock

to [Show all files in macOS Finder](https://apple.stackexchange.com/a/34872) open Finder and click 'comand+shift+h'

https://bhagavadgita.io/

Lackner [KMP](https://www.youtube.com/watch?v=RSBO1C_Du2U&list=PLQkwcJG4YTCS55alEYv3J8CD4BXhqLUuk&pp=iAQB)  
[Navigation KMP](https://www.youtube.com/watch?v=g4XSWQ7QT8g) 
[KMM](https://www.youtube.com/watch?v=7Wl-G8aXxDA&list=PLQkwcJG4YTCQxZMQdhR2_TNYa-jwnXUGJ&pp=iAQB)

Medium (KMP)
[background](https://medium.com/@OlgaDery/kotlin-multiplatform-evaluation-part-2-how-to-design-background-functionality-42eb74416d87)
[DataStore](https://medium.com/@stevdza-san/datastore-in-a-compose-multiplatform-for-ios-android-complete-guide-aa9370cdf33d)

https://github.com/blackstoneaudio/Kormatter
https://github.com/pearxteam/kormatter
https://jonas-rodehorst.dev/blog/koltin-string-formatting