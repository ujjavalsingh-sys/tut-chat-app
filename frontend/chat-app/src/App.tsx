import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router";
import { Login } from "./components/Login";
import { Dashboard } from "./components/Dashboard";
import { MessageList } from "./components/MessageList";
import { ErrorBoundary } from "./errorhandling/ErrorBoundary";
import { ErrorProvider } from "./errorhandling/ErrorProvider";

function App() {
  return (
    <ErrorBoundary>
      <ErrorProvider>
        <BrowserRouter>
          <Routes>
            <Route index element={<Login />} />
            <Route path="dashboard" element={<Dashboard />}>
              <Route index element={<MessageList />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </ErrorProvider>
    </ErrorBoundary>
  );
}

export default App;
