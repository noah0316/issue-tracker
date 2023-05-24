//
//  NibFactory.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/23.
//

import UIKit.UINib

enum TypeOfNib {
    case issueListCollectionViewCell
}

extension TypeOfNib {
    func makeNibRepresentation() -> NibRepresentation {
        switch self {
        case .issueListCollectionViewCell:
            return NibRepresentation(bundle: nil, nibName: IssueListCollectionViewCell.identifier)
        }
    }
}

struct NibRepresentation {
    let bundle: Bundle?
    let nibName: String
}

struct NibFactory {
    static func makeNib(for typeOfNib: TypeOfNib) -> UINib {
        let nibRepresntation = typeOfNib.makeNibRepresentation()
        let nib = UINib(nibName: nibRepresntation.nibName, bundle: nibRepresntation.bundle)
        return nib
    }
}
