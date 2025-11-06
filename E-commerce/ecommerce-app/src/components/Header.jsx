import { Link } from "react-router-dom";
import "./Header.css";

function Header() {
  return (
    <header className="header">
      <div className="logo">
        <Link to="/">MyStore</Link>
      </div>
      <nav className="nav-icons">
        <span className="icon">🛒</span>
        <span className="icon">🔔</span>
        <span className="icon">👤</span>
      </nav>
    </header>
  );
}

export default Header;
