import React from 'react'

const Header = ({toggleModal,nbOfContaxcts}) => {
  return (
    <header className="header">
        <div className='container'>
            <h3>
                Contact List ({nbOfContaxcts})
                <button onClick={()=>toggleModal(true)} className='btn'><i className='bi bi-plus-quare'></i>Add new Contact</button>

            </h3>
        </div>

    </header>
  )
}

export default Header;