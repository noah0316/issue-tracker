//
//  IssueTrackerTabBarController.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/10.
//

import UIKit

final class IssueTrackerTabBarController: UITabBarController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupUIAppearance()
    }
}

extension IssueTrackerTabBarController {
    private func setupUIAppearance() {
        self.setupTabBarUIAppearance()
    }
    
    private func setupTabBarUIAppearance() {
        let tabBarApepearance = UITabBarAppearance()
        tabBarApepearance.backgroundColor = ColorStyle.gray50
        self.tabBar.standardAppearance = tabBarApepearance
    }
}
