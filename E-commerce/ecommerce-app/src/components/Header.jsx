import { Link, useLocation } from "react-router-dom";
import { useState } from "react";
import { useSelector } from "react-redux";
import SearchBar from "./SearchBar";
import "./Header.css";

function Header({ onSearch }) {
  const [showProfile, setShowProfile] = useState(false);
  const totalQuantity = useSelector((state) => state.cart.totalQuantity);
  const location = useLocation();

  const showSearchBar = ['/'].includes(location.pathname);
  const showBackButton = location.pathname !== '/';
  return (
    
    <header className="header">
      
      <div className="logo">
        <Link to="/">
          <img 
            src="https://tse3.mm.bing.net/th/id/OIP.Q7SWgeO5ZqdVZxD6skmK6gHaE8?cb=ucfimgc2&w=3000&h=2000&rs=1&pid=ImgDetMain&o=7&rm=3" 
            alt="Logo" 
            className="logo-image" 
          />
        </Link>
      </div>
      
      {showSearchBar && <SearchBar onSearch={onSearch} />}
      
      <nav className="nav-icons">
        {showBackButton &&<Link to="/" className="home-btn">Home</Link>}
        <Link to="/cart" className="icon cart">
          🛒 <span className="cart-count">{totalQuantity > 9 ? '9+' : totalQuantity}</span>
        </Link>
        
        <span className="icon notification">🔔</span>
        
        <div className="profile-dropdown">
          <span 
            className="icon user" 
            onClick={() => setShowProfile(!showProfile)}
          >
            👤
          </span>
          {showProfile && (
            <div className="dropdown-menu">
              <Link to="/orders" onClick={() => setShowProfile(false)}>
                My Orders
              </Link>
              <Link to="/profile" onClick={() => setShowProfile(false)}>
                Profile
              </Link>
              <span onClick={() => {
                alert("Successfully logged out!");
                setShowProfile(false);
              }}>
                Logout
              </span>
            </div>
          )}
        </div>
      </nav>
    </header>
  );
}

export default Header;
