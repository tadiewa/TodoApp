import axios from 'axios';



const REST_API_BASE_URL = 'http://localhost:9290/v1/todo'


export const listTodos = () =>  axios.get(REST_API_BASE_URL);


 //export const createTodo = (todo)=> axios.post(REST_API_BASE_URL+'/create', todo);
export const createTodo = (todo) => axios.post(
    REST_API_BASE_URL+'/create',
    todo,
    {
      headers: {
        "Content-Type": "application/json",
      },
    }
  ).then((response) => response.data)
   .catch((error) => {
     console.error("Error creating todo:", error.response || error.message);
     throw error;
   });


   export const getTodo = (todoId)=> axios.get(REST_API_BASE_URL + '/' + todoId);
   export const updateTodo = (todoId , todo)=> axios.put(REST_API_BASE_URL + '/' + todoId,todo);
   export const removeTodo =(todoId) =>axios.delete(REST_API_BASE_URL+'/' + todoId);