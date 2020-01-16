import React, { useState, useEffect } from 'react';
import Facade from './ApiFacade'

export default function LoggedIn() {

  const [username, setUsername] = useState();
  const [role, setRole] = useState();

  const [data, setData] = useState([]);

  useEffect(() => {
    Facade.fetchUser().then(res => {
      setUsername(res.userName);
      setRole(res.roleList);
      Facade.fetchData2(res.userName).then(res => setData(res))
    }).catch(e => console.log(e));
  }, [])


  return (
    <div>
      {role === "Student" ? (
        <div>
          <h2>My Courses</h2>
          <h3>{username} - {role}</h3>
          <table className="table">
            <thead className="thead-dark">
              <tr>
                <th scope="col">Course Name</th>
                <th scope="col">Description</th>
                <th scope="col">Grade</th>
                <th scope="col">Passed</th>
              </tr>
            </thead>
            <tbody>
              {data.map((course, index) => <tr key={index}><td>{course.courseName}</td><td>{course.description}</td><td>{course.grade}</td><td>{course.passed}</td></tr>)}
            </tbody>
          </table>
        </div>
      ) : (
          <div>
            <h2>My Courses</h2>
            <h3>{username} - {role}</h3>
          </div>
        )}
    </div>
  )
}