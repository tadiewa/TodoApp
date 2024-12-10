import ListTodoComponent from "./components/ListTodoComponent"
import './App.css'
import HeaderComponent from "./components/HeaderComponent"
import FooterComponent from "./components/FooterComponent"
import { BrowserRouter,Route,Routes } from "react-router-dom"
import TodoComponent from "./components/TodoComponent"

function App() {
  
  return (
    <>
    <BrowserRouter>
    <HeaderComponent/>   
    <Routes>
      {/* // http://localhost:9290/v1/todo */}
       <Route path="/" element ={<ListTodoComponent/>}> </Route>
      {/* // http://localhost:9290/v1/todo */}
       <Route path="/Todos" element ={<ListTodoComponent/>}> </Route>
      {/* // http://localhost:9290/v1/todo/ */}
        <Route path="/add-todo" element ={<TodoComponent/>}></Route>
      {/* // http://localhost:9290/v1/todo/1 */}
       <Route path="/edit-todo/:id" element ={<TodoComponent/>}></Route>
        {/* // http://localhost:9290/v1/todo/1 */}
        <Route path="/delete-todo/:id" element ={<TodoComponent/>}></Route>

    </Routes>
    
    <FooterComponent/>
    </BrowserRouter>
    </>
  )
}

export default App
