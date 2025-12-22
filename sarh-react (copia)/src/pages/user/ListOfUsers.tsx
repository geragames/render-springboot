import { useAppSelector } from "../../hooks/store";


export function ListOfUsers() {
   
    const  users  = useAppSelector((state) => state.users);

    return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Contraseña</th>
        
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                   
                </tbody>
            </table>
        </div>
    );
}