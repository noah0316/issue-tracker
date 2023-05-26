//
//  CompositionRoot.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/25.
//

import UIKit

struct CompositionRoot {
    static func resolve() -> AppDependency {
        let issueTrackerTabBarController = IssueTrackerTabBarController()
        let issueListViewController = IssueListViewControllerFactory.make()
        issueTrackerTabBarController.setViewControllers(
            [UINavigationController(rootViewController: issueListViewController)],
            animated: true
        )
        
        return AppDependency(rootViewController: issueTrackerTabBarController)
    }
}
