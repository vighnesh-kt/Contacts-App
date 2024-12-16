import React from "react";
import Contacts from "./Contacts";

const ContactList = ({ data, currentPage, totalPages, getAllContacts }) => {
  console.log("ContactList data:", data); // Debugging log

  return (
    <main className="main">
      {data?.length > 0 ? (
        <>
          <ul className="contact__list">
            {data.map((contact) => {
              console.log("Rendering contact:", contact); // Log each contact
              return <Contacts contact={contact} key={contact.id} />;
            })}
          </ul>
          {totalPages > 1 && (
            <div className="pagination">
              <a
                onClick={() => getAllContacts(Math.max(currentPage - 1, 0))}
                className={currentPage === 0 ? "disabled" : ""}
              >
                &laquo;
              </a>
              {[...Array(totalPages).keys()].map((page) => (
                <a
                  onClick={() => getAllContacts(page)}
                  className={currentPage === page ? "active" : ""}
                  key={page}
                >
                  {page + 1}
                </a>
              ))}
              <a
                onClick={() =>
                  getAllContacts(Math.min(currentPage + 1, totalPages - 1))
                }
                className={currentPage + 1 === totalPages ? "disabled" : ""}
              >
                &raquo;
              </a>
            </div>
          )}
        </>
      ) : (
        <div>No Contacts. Please add a new contact.</div>
      )}
    </main>
  );
};

export default ContactList;
