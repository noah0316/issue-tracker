//
//  IssueListViewController.swift
//  IssueTracker
//
//  Created by Noah on 2023/05/19.
//

import Combine
import UIKit

final class IssueListViewController: UIViewController {
    @IBOutlet private weak var issueListView: IssueListView!
    @IBOutlet private weak var issueListLoadingIndicatorView: UIActivityIndicatorView!
    private let viewModel: IssueListViewModelable?
    private var cancellables: Set<AnyCancellable>
    
    required init?(coder: NSCoder) {
        self.viewModel = nil
        self.cancellables = Set<AnyCancellable>()
        super.init(coder: coder)
    }
    
    init?(coder: NSCoder, viewModel: IssueListViewModelable) {
        self.viewModel = viewModel
        self.cancellables = Set<AnyCancellable>()
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.bindViewModel()
        self.setUIAppearance()
        self.viewModel?.input.viewDidLoad()
    }
}

extension IssueListViewController {
    private func setUIAppearance() {
        self.enableLargeTitles()
    }
    
    private func enableLargeTitles() {
        self.navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    private func updateIssueListLoadingStatusUI(for loadingStatus: LoadingStatus) {
        switch loadingStatus {
        case .done:
            self.issueListLoadingIndicatorView.stopAnimating()
            self.issueListView.isHidden = false
            self.issueListLoadingIndicatorView.isHidden = true
        case .loading:
            self.issueListLoadingIndicatorView.startAnimating()
            self.issueListView.isHidden = true
            self.issueListLoadingIndicatorView.isHidden = false
        }
    }
    
}

extension IssueListViewController {
    // swiftlint:disable:next function_body_length
    private func bindViewModel() {
        self.viewModel?.output
            .issues
            .receive(on: DispatchQueue.main)
            .sink { error in
                // TODO: 구현 예정
            } receiveValue: { [weak self] issues in
                self?.issueListView.update(with: issues)
            }
            .store(in: &self.cancellables)
        
        self.viewModel?.output
            .issueListLoadingStatus
            .receive(on: DispatchQueue.main)
            .sink { [weak self] issueListLoadingStatus in
                self?.updateIssueListLoadingStatusUI(for: issueListLoadingStatus)
            }
            .store(in: &self.cancellables)
    }
}
