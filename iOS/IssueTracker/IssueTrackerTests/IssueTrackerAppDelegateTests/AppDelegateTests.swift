//
//  AppDelegateTests.swift
//  IssueTrackerTests
//
//  Created by Noah on 2023/05/26.
//

import XCTest

final class AppDelegateTests: XCTestCase {
    func test_테스트타겟이_실행되었을때_AppDelegateTestDouble을_사용하는가() {
        XCTAssertTrue(UIApplication.shared.delegate is AppDelegateTestDouble)
    }
    
    func test_didFinishLaunchingWithOptions가_호출되었을때_rootViewController를_설정하는가() {
        // Arrange
        let rootViewController = UIViewController()
        let appDelegate = self.makeAppDelegate(with: rootViewController)
        
        // Act
        _ = appDelegate.application(.shared, didFinishLaunchingWithOptions: nil)
        
        // Assert
        XCTAssertEqual(appDelegate.window?.rootViewController, rootViewController)
    }
}

extension AppDelegateTests {
    private func makeAppDelegate(with rootViewController: UIViewController) -> AppDelegate {
        return AppDelegate(
            dependency: AppDependency(
                rootViewController: rootViewController
            )
        )
    }
}
