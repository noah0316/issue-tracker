//
//  ViewControllerMakable.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/25.
//

import UIKit

protocol ViewControllerMakable {
    associatedtype ViewControllerType: UIViewController
    static func make() -> ViewControllerType
}
