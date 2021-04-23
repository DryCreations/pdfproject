/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupseven.pdfproject;

/**
 * @author hayde
 */

/// \brief An interface for creating PDF actions that can be undone/redone
/// \ref t10_2 "Task 10.2"
@FunctionalInterface
public interface TriConsumer<T, U, V> {

    /// \brief executes the action
    /// \ref t10_2 "Task 10.2"
    void accept(T t, U u, V v);
}
