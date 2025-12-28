import type { ReactNode } from "react";
import React from "react";
import { ErrorToast, type ErrorBoundaryState } from "./ErrorToast";

interface ErrorBoundaryProps {
  children: ReactNode;
}

export class ErrorBoundary extends React.Component<
  ErrorBoundaryProps,
  ErrorBoundaryState
> {
  constructor(props: ErrorBoundaryProps) {
    super(props);
    this.state = {};
  }

  static getDerivedStateFromError(error: Error): ErrorBoundaryState {
    return { errorMessage: error.message };
  }

  componentDidCatch(error: Error, errorInfo: React.ErrorInfo): void {
    console.log(`ErrorBoundary caught:`, error, errorInfo);
  }

  render(): ReactNode {
    return (
      <>
        <ErrorToast errorMessage={this.state.errorMessage} />
        {this.props.children}
      </>
    );
  }
}
