package com.tp2.tp2.controller;

import com.tp2.tp2.model.Filiere;
import com.tp2.tp2.service.FiliereService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FiliereController extends HttpServlet{

        private FiliereService filiereService;

        @Override
        public void init() {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            filiereService = new FiliereService(validator);
        }

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String action = req.getParameter("action");
            if (action == null) action = "list";

            switch (action) {
                case "add":
                    req.getRequestDispatcher("filiere/add.jsp").forward(req, resp);
                    break;
                case "edit":
                    Integer id = Integer.valueOf(req.getParameter("id"));
                    Optional<Filiere> filiere = filiereService.findFiliereById(id);
                    req.setAttribute("filiere", filiere.orElse(null));
                    req.getRequestDispatcher("filiere/edit.jsp").forward(req, resp);
                    break;
                default:
                    List<Filiere> list = filiereService.findAllFilieres();
                    req.setAttribute("filieres", list);
                    req.getRequestDispatcher("filiere/list.jsp").forward(req, resp);
            }

        }

        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");

            switch (action) {

                case "create":
                    Filiere filiere = new Filiere();
                    filiere.setCode(req.getParameter("code"));
                    filiere.setNom(req.getParameter("libelle"));

                    filiereService.createFiliere(filiere);
                    resp.sendRedirect("filieres");
                    break;

                case "update":
                    Integer id = Integer.valueOf(req.getParameter("id"));

                    Filiere filiere1 = new Filiere();
                    filiere1.setId(id);
                    filiere1.setNom(req.getParameter("libelle"));
                    filiere1.setCode(req.getParameter("code"));

                    filiereService.updateFiliere(filiere1);
                    resp.sendRedirect("filieres");
                    break;

                case "delete":
                    Integer deletedId = Integer.valueOf(req.getParameter("id"));
                    Optional<Filiere> fil = filiereService.findFiliereById(deletedId);

                    fil.ifPresent(filiereService::deleteFiliere);
                    resp.sendRedirect("filieres");
                    break;

            }
        }
}
