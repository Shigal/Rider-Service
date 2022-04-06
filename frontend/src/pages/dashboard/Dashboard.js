import React, { useState, useEffect } from "react"
import { useDispatch } from "react-redux";
import {fetchUserData} from '../../services/authService'
import { useNavigate } from "react-router-dom";
// import {Menu, Button} from 'antd'
// import { Button, Menu } from 'antd';
// import { getCurrentUser } from "../../redux/authActions";
import { Layout, Button, Menu, Avatar, Typography, Image } from 'antd';
// import { Typography } from 'antd';

const { SubMenu } = Menu;
const { Title } = Typography;

const { Header, Footer, Sider, Content } = Layout;

export const Dashboard = (props) => {
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState({});
    const dispatch = useDispatch();
    const navigate = useNavigate()


    useEffect(() => {
        fetchUserData().then((response) => {
            setData(response.data)
        }).catch((e) => {
            localStorage.clear();
            navigate("/login");
        })
    }, [])

    console.log("response.data->", );

    const logout = () => {
        localStorage.clear();
        navigate("/");
    }

    return(
        <div>
            {/* <Menu theme="dark" mode="inline" >
            <Menu.Item key="dashboard">Dashboard</Menu.Item>
            {/* <Menu.Item key="user">{data && `${data.firstName} ${data.lastName}`}</Menu.Item > */}
            {/* <Menu.Item key={data}>{data && `${data.firstName} ${data.lastName}`}</Menu.Item> */}
            {/* <Menu.Item key="logout"><Button onClick = {logout}>Logout</Button></Menu.Item> */}
            {/* </Menu> */} 


            <Layout>
            <Header>
            <Title level={3} style={{color: 'white', padding: '10px'}}>
            {data && `${data.firstName} ${data.lastName}`}
                <Button type="primary" style={{float: 'right'}} onClick={logout}>Logout</Button>
                </Title>
            {/* <Avatar style={{float:'right'}} size={64} src='./delivery.png' /> */}
            </Header>
                <Layout>
                <Sider>
                    <Menu theme="dark">
                        <Menu.Item key='dashboard'>
                            <Avatar style={{float:'left', padding: '5px'}} size={30} src='./dashboard.png' />
                            <Title level={5} >Dashboard</Title>
                        </Menu.Item>
                            <Menu.Item key="Restaurant" >
                            {/* <Image width={40} height={40}  src="./restaurant.png"/> */}
                            <Avatar style={{float:'left', padding: '5px'}} size={30} src='./restaurant.png' />
                            <Title level={5}>Restaurant</Title>
                                </Menu.Item>
                            <Menu.Item key="Customer">
                            <Avatar style={{float:'left', padding: '5px'}} size={30} src='./customer.png' />
                            <Title level={5}>Customer</Title>
                            </Menu.Item>
                    </Menu>
                </Sider>
                    <Content style={{padding: '0 50px'}}>
                        Content
                        {/* <div style={{background: '#fff', padding: 24, minHeight: 580}}>content</div> */}
                        </Content>
                </Layout>
                <Footer>Footer</Footer>
            </Layout>
        </div>
       
    )
    
}

