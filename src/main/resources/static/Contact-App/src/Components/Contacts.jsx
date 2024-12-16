import React from "react";
import { Link } from "react-router-dom";

const Contacts = ({ contact }) => {
  const { address } = contact; // Destructure the address field
  const formattedAddress = `${address.street}, ${address.city}, ${address.state} - ${address.postalCode}`;

  return (
    <Link to={`/contacts/${contact.id}`} className="contact__item">
      <div className="contact__header">
        <img
          className="contact__image"
          src={contact.photoUrl}
          alt={contact.name}
        />
      </div>
      <div className="contact__details">
        <p className="contact_name">{contact.name}</p>
        <p className="contact_title">{contact.title}</p>
      </div>
      <div className="contact__body">
        <p>
          <i className="bi bi-envelope"></i> {contact.email}
        </p>
        <p>
          <i className="bi bi-geo"></i> {formattedAddress}
        </p>
        <p>
          <i className="bi bi-telephone"></i> {contact.phone}
        </p>
        <p>
          {contact.status === "ONLINE" ? (
            <i className="bi bi-check-circle"></i>
          ) : (
            <i className="bi bi-x-circle"></i>
          )}
          {contact.status}
        </p>
      </div>
    </Link>
  );
};

export default Contacts;
