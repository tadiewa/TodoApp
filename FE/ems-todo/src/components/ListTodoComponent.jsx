import React ,{useEffect, useState}from 'react'
import { listTodos, removeTodo } from '../services/TodoServices'
import { useNavigate } from 'react-router-dom'

const ListTodoComponent = () => {

 const [todos , setTodos]= useState([])
 const navigator = useNavigate();

 useEffect(()=> {
getAllTodos()

},[])

 function getAllTodos(){
  listTodos().then((response)=>{
    setTodos(response.data.content);
   }).catch(error =>{
   
   console.error(error);
   
   })

 }


function addTodo(){

  navigator('/add-todo')

}
function deleteTodo(id){
  console.log(id);
  
  removeTodo(id).then((response)=>{
    getAllTodos();

  }).catch(error=>{
    
    console.error(error);
  })

}

function updateTodo(id){
  console.log(id);

  navigator(`/edit-todo/${id}`)
}


  return (
    <div className='container'> 
      <h2 className='text=center'>List of Todos</h2>
      <button className='btn btn-primary mb-2' onClick={addTodo}>Create Todo</button>
      <table className='table table-striped table-bordered'>
        <thead>
            <tr> 
              <th>ID</th>
                <th>CreatedBy</th>
                <th>Task</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
{
    todos.map(todo=>
        <tr key={todo.id}>
                         <td>{todo.id}</td>
                         <td>{todo.createdBy}</td>
                         <td>{todo.title}</td>
                         <td>{todo.description}</td>
                         <td>
                          <button className='btn btn-info' onClick={()=>updateTodo(todo.id)}>Update</button>
                          <button className='btn btn-danger' onClick={()=>deleteTodo(todo.id)}>Delete</button>
                         </td>
                       </tr>
    )
}

        </tbody>
      </table>



    </div>
  )
}

export default ListTodoComponent



// import React, { useEffect, useState } from "react";
// import { listTodos } from "../services/TodoServices";

// const ListTodoComponent = () => {
//   const [todos, setTodos] = useState([]);

//   useEffect(() => {
//     let isMounted = true;

//     listTodos()
//       .then((response) => {
//         if (isMounted) {
//           console.log(response.data); // Debugging response data
//           setTodos(response.data.content); // Ensure response.data is an array
//         }
//       })
//       .catch((error) => {
//         console.error(error);
//       });

//     return () => {
//       isMounted = false; // Cleanup
//     };
//   }, []);

//   return (
//     <div className="container">
//       <h2>List of Todos</h2>
//       <table className="table table-striped table-bordered">
//         <thead>
//           <tr>
//             <th>Todo Id</th>
//             <th>Todo</th>
//             <th>Email</th>
//           </tr>
//         </thead>
//         <tbody>
//           {Array.isArray(todos) ? (
//             todos.map((todo) => (
//               <tr key={todo.id}>
//                 <td>{todo.completed}</td>
//                 <td>{todo.title}</td>
//                 <td>{todo.description}</td>
//               </tr>
//             ))
//           ) : (
//             <tr>
//               <td colSpan="3">No todos available</td>
//             </tr>
//           )}
//         </tbody>
//       </table>
//     </div>
//   );
// };

// export default ListTodoComponent;
