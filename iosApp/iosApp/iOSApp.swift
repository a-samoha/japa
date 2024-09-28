import SwiftUI

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            IosScreenLockManager().keepScreenOn(true)
            ContentView()
        }
    }
}