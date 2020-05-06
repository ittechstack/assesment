import {SudorApp} from "./SudorApp";
import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16'
import renderer from 'react-test-renderer';

configure({adapter: new Adapter()});

describe('SudorApp component testing', () => {
    it('render correctly SudorApp component', () => {
        const wrapper = renderer.create(<SudorApp/>).toJSON();

        expect(wrapper).toMatchSnapshot();
    });

    it('should render header', () => {
        const wrapper = shallow(<SudorApp/>);
        expect(wrapper.contains(<h1>Sudor Application</h1>)).toBe(true)
    });
});