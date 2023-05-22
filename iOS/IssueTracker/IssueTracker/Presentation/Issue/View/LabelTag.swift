//
//  LabelTag.swift
//  IssueTracker
//
//  Created by ilim on 2023/05/22.
//

import UIKit

class LabelTag: UILabel {
    private static let padding = UIEdgeInsets(top: 4.0, left: 16.0, bottom: 4.0, right: 16.0)
    override var intrinsicContentSize: CGSize {
        var contentSize = super.intrinsicContentSize
        contentSize.width += Self.padding.left + Self.padding.right
        contentSize.height += Self.padding.top + Self.padding.bottom
        return contentSize
    }
    
    func configure(name: String) {
        text = name
        font = UIFont.systemFont(ofSize: 12, weight: .bold, width: .standard)
        textColor = .white
        setBackgroundColor()
        self.sizeToFit()
    }
    
    override func drawText(in rect: CGRect) {
        super.drawText(in: rect.inset(by: Self.padding))
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        layer.cornerRadius = ComponentStyle.Radius.regular
        layer.masksToBounds = true
    }
    
    func setBackgroundColor() {
        self.backgroundColor = ColorStyle.issueTrackerBlue
    }
}
