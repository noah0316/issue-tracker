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
    @IBOutlet private weak var retryRequestIssuesButton: UIButton!
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
        self.setupUIAppearance()
        self.viewModel?.input.viewDidLoad()
    }
    
    @IBAction private func retryRequestIssues(_ sender: UIButton) {
        self.viewModel?.input.fetchIssues()
    }
}

// MARK: - Set up UIApeearance

extension IssueListViewController {
    private func setupUIAppearance() {
        self.enableLargeTitles()
        self.hiddenRetryRequestIssuesButton()
    }
    
    private func enableLargeTitles() {
        self.navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    private func hiddenRetryRequestIssuesButton() {
        self.retryRequestIssuesButton.isHidden = true
    }
    
    private func showRetryRequestIssuesButton() {
        self.retryRequestIssuesButton.isHidden = false
    }
    
    private func updateUIForDoneLoadingStatus() {
        self.issueListLoadingIndicatorView.stopAnimating()
        self.issueListView.isHidden = false
        self.issueListLoadingIndicatorView.isHidden = true
    }
    
    private func updateUIForLoadingStatus() {
        self.hiddenRetryRequestIssuesButton()
        self.issueListLoadingIndicatorView.startAnimating()
        self.issueListView.isHidden = true
        self.issueListLoadingIndicatorView.isHidden = false
    }
    
    private func updateIssueListLoadingStatusUI(for loadingStatus: LoadingStatus) {
        switch loadingStatus {
        case .done:
            self.updateUIForDoneLoadingStatus()
        case .loading:
            self.updateUIForLoadingStatus()
        }
    }
}

// MARK: - ViewModel binding

extension IssueListViewController {
    // swiftlint:disable:next function_body_length
    private func bindViewModel() {
        self.viewModel?.output
            .issues
            .receive(on: DispatchQueue.main)
            .sink { [weak self] issues in
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
        
        self.viewModel?.output
            .alertErrorMessage
            .receive(on: DispatchQueue.main)
            .sink { alertMessage in
                self.presentErrorAlert(with: alertMessage)
            }
            .store(in: &self.cancellables)
        
        self.viewModel?.output
            .retriableError
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in
                self?.setupShowRetryRequestIssuesButton()
            }
            .store(in: &self.cancellables)
    }
}

// MARK: - present alert

extension IssueListViewController {
    private func makeErrorAlertController(with message: String) -> UIAlertController {
        let errorAlertController = UIAlertController(
            title: StringLiteral.ErrorAlertController.title,
            message: message,
            preferredStyle: UIAlertController.Style.alert
        )
        
        return errorAlertController
    }
    
    private func addActionToErroralertController(for errorAlertController: UIAlertController) {
        let confirmAction = UIAlertAction(
            title: StringLiteral.ErrorAlertController.confirm,
            style: .default
        )
        errorAlertController.addAction(confirmAction)
    }
    
    private func presentErrorAlert(with message: String) {
        let errorAlertController = self.makeErrorAlertController(with: message)
        self.addActionToErroralertController(for: errorAlertController)
        self.navigationController?.present(errorAlertController, animated: true)
    }
}

// MARK: - show retry request issue button

extension IssueListViewController {
    private func setupShowRetryRequestIssuesButton() {
        self.showRetryRequestIssuesButton()
        self.issueListLoadingIndicatorView.stopAnimating()
        self.issueListLoadingIndicatorView.isHidden = true
    }
}
