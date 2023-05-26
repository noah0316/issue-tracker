//
//  AppDelegateTestDouble.swift
//  IssueTrackerTests
//
//  Created by Noah on 2023/05/26.
//

import UIKit

final class AppDelegateTestDouble: UIResponder, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        return true
    }
}
