//
//  main.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/26.
//

import UIKit

UIApplicationMain(
    CommandLine.argc,
    CommandLine.unsafeArgv,
    NSStringFromClass(UIApplication.self),
    NSStringFromClass(NSClassFromString("IssueTrackerTests.AppDelegateTestDouble") ?? AppDelegate.self)
)
