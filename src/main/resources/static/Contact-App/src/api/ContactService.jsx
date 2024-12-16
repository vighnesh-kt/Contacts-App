import axios from "axios";

const API_URL = "http://localhost:8080/contacts";

export async function createContact(contact) {
  return axios.post(`${API_URL}/createcontact`, contact);
}

// Fetch all contacts
export async function getAllContacts() {
  return axios.get(`${API_URL}/findall`); // Use template literals to append endpoint
}

// Fetch paginated contacts
export async function getContactsPage(page = 0, size = 10) {
  return axios.get(`${API_URL}/getpage?page=${page}&size=${size}`); // Fix incorrect usage of API_URL
}

// Fetch a single contact by ID
export async function getContactById(id) {
  return axios.get(`${API_URL}/getcontact?id=${id}`); // Use axios.get directly
}

// Delete a contact by ID
export async function deleteContact(id) {
  return axios.delete(`${API_URL}/${id}`); // Consistently use axios.delete
}

export async function updatePhoto(formData) {
  return axios.put(`${API_URL}/image`, formData);
}
