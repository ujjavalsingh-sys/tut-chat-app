import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router";
import { Login } from "./components/landing/Login";
import { Dashboard } from "./components/dashboard/Dashboard";
import { NewChat } from "./components/chat/NewChat";
import { DashboardNoSelection } from "./components/dashboard/DashboardNoSelection";
import { Provider } from "react-redux";
import { store } from "./store/store";
import { ConversationChat } from "./components/chat/ConversationChat";
import { Landing } from "./components/landing/Landing";
import { ProtectedRoute } from "./components/landing/ProtectedRoute";
import { PublicRoute } from "./components/landing/PublicRoute";
import { MessageToast } from "./errorhandling/MessageToast";

function App() {
  return (
    <div className="flex h-screen flex-col justify-center items-center">
      <Provider store={store}>
        <MessageToast />
        <BrowserRouter>
          <Routes>
            <Route index element={<Landing />} />
            <Route
              path="login"
              element={
                <PublicRoute>
                  <Login />
                </PublicRoute>
              }
            />
            <Route
              path="dashboard"
              element={
                <ProtectedRoute>
                  <Dashboard />
                </ProtectedRoute>
              }
            >
              <Route index element={<DashboardNoSelection />} />
              <Route path="person/:id" element={<NewChat />} />
              <Route path="conversation/:id" element={<ConversationChat />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </Provider>
    </div>
  );
}

export default App;
