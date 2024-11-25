import React from "react";
import { Link } from "react-router-dom";

const Contacts = ({contact}) => {
  return (
    <Link to={`/contacts/${contact.id}`}
      className="contact_item"
      href=""
    >
      <div className="contact_header">
        <img src={contact.photoUrl} alt={contact.name} />
      </div>
      <div className="contact_details" >
        <p className="contact_name">{contact.name}</p>
        <p className="contact_title">{contact.title}</p>
      </div>
      <div>
        <p><i className="bi bi-envelope"></i>{contact.email}</p>
        <p><i className="bi bi-geo"></i>{contact.address}</p>
        <p><i className="bi bi-telephone"></i>{contact.phone}</p>
        <p>{contact.status === 'Active' ?<i  className='bi bi-check-circle'></i>:
        <i className="bi bi-x-circle"></i>
        }{contact.status}</p>
      </div>
    </Link>
  );
};

export default Contacts;
