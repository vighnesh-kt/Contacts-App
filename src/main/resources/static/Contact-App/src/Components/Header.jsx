import React from "react";

const Header = ({ toggleModal, nbOfContacts }) => {
  return (
    <header className="header">
      <div className="container">
        <h3>
          Contact List ({nbOfContacts})
          <button onClick={() => toggleModal(true)} className="btn">
            <i className="bi bi-plus-quare"></i>Add new Contact
          </button>
        </h3>
      </div>
    </header>
  );
};

export default Header;
