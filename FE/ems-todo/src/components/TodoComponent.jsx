import React, { useEffect, useState } from 'react'
import { createTodo, getTodo, updateTodo } from '../services/TodoServices'
import { useNavigate , useParams} from 'react-router-dom'

const TodoComponent = () => {

const[title,setTitle]= useState('')
const[description ,setDescription]= useState('')
const[completed ,setCompleted]= useState('')
const[createdBy ,setCreatedBy]= useState('')
const[dueDate ,setDueDate]= useState('')

const {id} = useParams();

const [errors , setErrors] =useState ({
 
    title:'',
    description:'',
    createdBy:'',
    dueDate:''
})



 const navigator = useNavigate();
  useEffect(()=>{

   if(id){


    getTodo(id).then((response)=> {
        setTitle(response.data.title);
        setDescription(response.data.description);
        setCompleted(response.data.completed);
        setCreatedBy(response.data.createdBy);
        setDueDate(response.data.dueDate);

    }).catch(error=> {

        console.error(error);
    })
   }



  },[id])

function handleTitle(e){

    setTitle(e.target.value);
}
function handledescription(e){

    setDescription(e.target.value);
}
function handlecreatedBy(e){

    setCreatedBy(e.target.value);
}
function handledueDate(e){

    setDueDate(e.target.value);
}
function handlecompleted(e){

    setCompleted(e.target.checked);
}

function saveOrUpdateTodo(e){
    
    e.preventDefault();

     if(validateForm()){   
        const todo = {title,description,createdBy,completed,dueDate}
        console.log(todo)
        
        if(id){
            updateTodo(id,todo).then((response)=>{
             console.log(response.data)
             navigator('/Todos')

            }).catch(error=> {

                console.error(error);
            })
        }else {

            createTodo(todo).then((response)=>{
                console.log(response.data);
                navigator('/')
            }).catch(error=> {

                console.error(error);
            })

        }
        
       
        
    }

 
}


function validateForm(){
    let valid = true;


    const errorsCopy ={... errors}

    if(title.trim()){
        errorsCopy.title='';
    }else {
     errorsCopy.title ='title is required';
     valid = false;
    }
    
    if(createdBy.trim()){
        errorsCopy.createdBy='';
    }else {
     errorsCopy.createdBy ='createdBy is required';
     valid = false;
    }
    
    if(description.trim()){
        errorsCopy.description='';
    }else {
     errorsCopy.description ='description is required';
     valid = false;
    }
    
    if(dueDate.trim()){
        errorsCopy.dueDate='';
    }else {
     errorsCopy.dueDate ='dueDate is required';
     valid = false;
    }

    setErrors(errorsCopy);
    return valid;
}

function pageTitle(){
    
    if(id){
        return <h2 className='text-center'>Update Todo</h2>
    }else {
   <h2 className='text-center'>Add Todo</h2>
    }


}



  return (
    <div className='container'>
        <br></br>
        <div className='row'>
           <div className='card col-md-6 offset-md-3 offset-md-3'>
           {

            pageTitle()
           }
            <div className='card-body'>
               <form>
                   <div className ='form-group mb-2'>
                    <label className='form-label'>Title</label>
                    <input   
                    type='text'
                    placeholder='Enter title'
                    name='title'
                    value={title}
                    className={`form-control ${ errors.title ? 'is-invalid': ''}`}
                    onChange={handleTitle}    
                    >
                    </input>
                    {errors.title && <div className='invalid-feedback'> {errors.title} </div> }
                   </div>

                   <div className ='form-group mb-2'>
                    <label className='form-label'>description</label>
                    <input   
                    type='text'
                    placeholder='Enter description'
                    name='description'
                    value={description}
                    className={`form-control ${ errors.description ? 'is-invalid': ''}`}
                    onChange={handledescription}    
                    >
                    </input>
                    {errors.description && <div className='invalid-feedback'> {errors.description} </div> }
                   </div>
                    
                   <div className ='form-group mb-2'>
                    <label className='form-label'>createdBy</label>
                    <input   
                    type='text'
                    placeholder='createdBy'
                    name='createdBy'
                    value={createdBy}
                    className={`form-control ${ errors.createdBy ? 'is-invalid': ''}`}
                    onChange={handlecreatedBy}    
                    >
                    </input>
                    {errors.createdBy && <div className='invalid-feedback'> {errors.createdBy} </div> }
                   </div>

                   <div className ='form-group mb-2'>
                    <label className='form-label'>dueDate</label>
                    <input   
                    type='date'
                    placeholder='Enter dueDate (YYYY-MM-DD)'
                    name='dueDate'
                    pattern='\d{4}-\d{2}-\d{2}'
                    value={dueDate}
                    className={`form-control ${ errors.dueDate ? 'is-invalid': ''}`}
                    onChange={handledueDate}    
                    >
                    </input>
                    {errors.dueDate && <div className='invalid-feedback'> {errors.dueDate} </div> }
                   </div> 
                   <div className ='form-group mb-2'>
                    <label className='form-label'>completed</label>
                    <input   
                    type='checkbox'
                    placeholder='Enter completed'
                    name='completed'
                    checked={completed}
                    className='form-check-input ms-2'
                    onChange={handlecompleted}    
                    >
                    </input>
                   </div>
        
                   <button className="btn btn-success" onClick={saveOrUpdateTodo}>Submit</button>

               </form>



            </div>

           </div>


        </div>
 
 

    </div>
  )
}

export default TodoComponent