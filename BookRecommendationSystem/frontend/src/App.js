import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Nav,
  NavItem,
  NavLink,
  TabContent,
  TabPane,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  ListGroup,
  ListGroupItem,
} from "reactstrap";
import classnames from "classnames";  
import BookModal from "./components/BookModal";
import AddBookForm from "./components/AddBookForm"; 
import DeleteBookForm from "./components/DeleteBookForm";
import BookSearchPage from "./components/BookSearchPage";
import UpdateBookForm from "./components/UpdateBookForm";
import "./App.css";

const backendUrl = 'https://bookrecommendationsystem-1.onrender.com';

const App = () => {
  const [bookList, setBookList] = useState([]); 
  const [activeItem, setActiveItem] = useState({}); 
  const [modal, setModal] = useState(false); 
  const [isLoading, setIsLoading] = useState(false); 
  const [error, setError] = useState(null); 
  const [activeTab, setActiveTab] = useState("1"); 

  const fetchBooks = async () => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await axios.get(`${backendUrl}/api/BookRec/`);
      setBookList(response.data);
    } catch (error) {
      console.error("Error fetching books:", error);
      setError("Error fetching books");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchBooks(); 
  }, []);

  const handleBookClick = (book) => {
    setActiveItem(book);
    setModal(true);
  };

  const toggle = (tab) => {
    if (activeTab !== tab) setActiveTab(tab);
  };

  return (
    <div className="container">
      <h1>BookRec</h1>
      <Nav tabs >
        <NavItem >
          <NavLink
            className={classnames({ active: activeTab === "1" })}
            onClick={() => toggle("1")} id="tabs"
          >
            All Books
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({ active: activeTab === "2" })}
            onClick={() => toggle("2")} id="tabs"
          >
            Add Book
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({ active: activeTab === "3" })}
            onClick={() => toggle("3")} id="tabs"
          >
            Search Book
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({ active: activeTab === "4" })}
            onClick={() => toggle("4")} 
          >
            Update Book
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            className={classnames({ active: activeTab === "5" })}
            onClick={() => toggle("5")} 
          >
            Delete Book
          </NavLink>
        </NavItem>
      </Nav>

      <TabContent activeTab={activeTab}>
        <TabPane tabId="1">
          {isLoading && <p>Loading books...</p>}
          {error && <p>Error fetching books: {error.message}</p>}
          <ul className="list-group list-group-flush border-top-0">
            {bookList.map((item) => (
              <li
                key={item.id}
                className="list-group-item"
                onClick={() => handleBookClick(item)}
              >
                {item.title} by {item.authors}
              </li>
            ))}
          </ul>
        </TabPane>

        <TabPane tabId="2">
        <AddBookForm onBookAdded={fetchBooks} />
        </TabPane>
        <TabPane tabId="3">
           <BookSearchPage/>
        </TabPane>
        <TabPane tabId="4">
        <UpdateBookForm onBookUpdated={fetchBooks} /> 
        </TabPane>
        <TabPane tabId="5">
          <DeleteBookForm/>
          </TabPane>
        </TabContent>
      <BookModal
        isOpen={modal}
        toggle={() => setModal(false)}
        activeItem={activeItem}
      />
    </div>
  );
};

export default App;
