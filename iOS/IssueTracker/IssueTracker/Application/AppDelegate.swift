//
//  AppDelegate.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/10.
//

import UIKit

final class AppDelegate: UIResponder, UIApplicationDelegate {
    private let appDependency: AppDependency
    var window: UIWindow?
    
    /// 프레임워크가 호출하는 생성자입니다.
    private override init() {
        self.appDependency = CompositionRoot.resolve()
        super.init()
    }
    
    /// 테스트 시 호출할 생성자입니다.
    init(dependency: AppDependency) {
        self.appDependency = dependency
    }
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        let window = UIWindow(frame: UIScreen.main.bounds)
        window.rootViewController = self.appDependency.rootViewController
        window.makeKeyAndVisible()
        self.window = window
        
        return true
    }
}
