//
//  FontStyle.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/12.
//

import UIKit.UIFont

enum FontStyle {
    static let body: UIFont = .systemFont(ofSize: FontSize.medium, weight: .regular)
    static let bodyMedium: UIFont = .systemFont(ofSize: FontSize.medium, weight: .bold)
    static let title: UIFont = .systemFont(ofSize: FontSize.large, weight: .bold)
    static let titleMedium: UIFont = .systemFont(ofSize: FontSize.xLarge, weight: .regular)
    static let titleStrong: UIFont = .systemFont(ofSize: FontSize.doubleXLarge, weight: .regular)
    static let button: UIFont = .systemFont(ofSize: FontSize.medium, weight: .bold)
    static let buttonWeak: UIFont = .systemFont(ofSize: FontSize.small, weight: .bold)
    static let buttonStrong: UIFont = .systemFont(ofSize: FontSize.large, weight: .bold)
    static let placeHolder: UIFont = .systemFont(ofSize: FontSize.medium, weight: .bold)
    static let caption: UIFont = .systemFont(ofSize: FontSize.small, weight: .bold)
    static let label: UIFont = .systemFont(ofSize: FontSize.small, weight: .bold)
}

private extension FontStyle {
    enum FontSize {
        static let doubleXLarge: CGFloat = 32
        static let xLarge: CGFloat = 24
        static let large: CGFloat = 18
        static let medium: CGFloat = 15
        static let small: CGFloat = 12
    }
}
