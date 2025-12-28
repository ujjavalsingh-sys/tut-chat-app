import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router";
import { Login } from "./components/Login";
import { Dashboard } from "./components/Dashboard";
import { MessageList } from "./components/MessageList";
import { ErrorBoundary } from "./errorhandling/ErrorBoundary";
import { ErrorProvider } from "./errorhandling/ErrorProvider";
import { DashboardNoSelection } from "./components/DashboardNoSelection";
import { Provider } from "react-redux";
import { store } from "./store/store";

function App() {
  return (
    <ErrorBoundary>
      <ErrorProvider>
        <Provider store={store}>
          <BrowserRouter>
            <Routes>
              <Route index element={<Login />} />
              <Route path="dashboard" element={<Dashboard />}>
                <Route index element={<DashboardNoSelection />} />
                <Route path="person/:id" element={<MessageList />} />
              </Route>
            </Routes>
          </BrowserRouter>
        </Provider>
      </ErrorProvider>
    </ErrorBoundary>
  );
}

export default App;
