import items from "../data/sidebar.json";
import { Outlet } from "react-router-dom"
import { SidebarItemType } from "../interfaces/sidebar";
import Sidebar from "./Sidebar";
import { Header } from "./Header";

const Layout = () => {

    const itemsSidebar: SidebarItemType[] = items
    return (
        <div className="layout">
            
           <Sidebar items={itemsSidebar} />
           <Header/>
           <Outlet/>
     
        </div>
          
    );
}

export default Layout;